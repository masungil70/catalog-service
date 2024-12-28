FROM base-catalog-service:latest
USER spring
COPY workspace/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"] 