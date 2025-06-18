package model;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
public class LoggingAspect {
    private static final String LOG_FILE = "log.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @Around("@annotation(model.Log)")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        Object[] args = joinPoint.getArgs();
        String params;
        if (args.length == 0) {
            params = "sin parametros";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                if (i > 0) sb.append("|");
                sb.append(args[i] != null ? args[i].toString() : "null");
            }
            params = sb.toString();
        }
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String logLine = String.format("\"%s\", \"%s\", \"%s\"%n", methodName, params, timestamp);
        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            fw.write(logLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return joinPoint.proceed();
    }
} 