GESTOR DE TAREAS - Iván Bacho Cifuentes

Proyecto Integrado
2.º DAM
CESUR Plaza Elíptica
Curso 2024-2025  




1. DESCRIPCIÓN

Aplicación de escritorio para crear, editar y filtrar tareas diarias.
• CRUD completo sobre tareas
• Filtro rápido L-V y por estado (Pendientes / Hechas)
• Acceso rápido «Dejar para mañana»
• Indicador de progreso
• Atajos globales: Ctrl+N · Ctrl+D · Supr
• Persistencia local
• Tests unitarios
• Fat-JAR auto-contenedor



2. REQUISITOS

JDK 17 o superior  
Maven 3.9.x  



3. COMPILACIÓN Y PRUEBAS

mvn clean test           ← compila + JUnit  
mvn javafx:run           ← ejecuta en modo desarrollo  



4. EMPAQUETADO Y EJECUCIÓN

mvn clean package        ← genera target/GestorTareas.jar  
java -jar target/GestorTareas.jar  
Windows: ejecutar run.bat (incluido)



5. GUÍA RÁPIDA DE USO

+ Nueva      → crear tarea  
Editar       → modificar tarea seleccionada  
Hecha        → marcar completada  
⇢ Mañana     → posponer un día  
Panel L-V    → filtrar por día (clic «Todos» para reset)

