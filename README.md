# KraftbaseAssignment

**Step 1: Set Up InfluxDB**

1. Download InfluxDB from the official website (https://portal.influxdata.com/downloads).
2. Install InfluxDB on the machine where you want to run it. Follow the installation instructions for your operating system.
3. Start the InfluxDB service or daemon. The process to start InfluxDB varies depending on your OS.
4. Access the InfluxDB administration interface through a web browser. By default, it runs on http://localhost:8086.
5. In the InfluxDB administration interface, create a new database named "metrics" where we'll store the metrics data.

**Step 2: Set Up Telegraf**

1. Download Telegraf from the official website (https://portal.influxdata.com/downloads).
2. Install Telegraf on the same machine where InfluxDB is running. Follow the installation instructions for your OS.
3. Open the Telegraf configuration file (`telegraf.conf`). You can usually find it at `/etc/telegraf/telegraf.conf` or `/etc/telegraf.conf` on Linux systems.
4. Configure the JMX input plugin to collect metrics from the Spring Boot application. Add the following lines to the `telegraf.conf` file:

```conf
[[inputs.jmx]]
  ## URL of the JMX endpoint (change <YOUR_SPRING_BOOT_APP_HOSTNAME> to the actual hostname or IP address of the machine running the Spring Boot app)
  service_url = "service:jmx:rmi:///jndi/rmi://<YOUR_SPRING_BOOT_APP_HOSTNAME>:9999/jmxrmi"
  [[inputs.jmx.metric]]
    name  = "heap_memory_usage"
    mbean = "java.lang:type=Memory"
    attribute = "HeapMemoryUsage"
    paths = ["used"]
  [[inputs.jmx.metric]]
    name  = "non_heap_memory_usage"
    mbean = "java.lang:type=Memory"
    attribute = "NonHeapMemoryUsage"
    paths = ["used"]
```

**Step 3: Set Up Grafana**

1. Download Grafana from the official website (https://grafana.com/grafana/download).
2. Install Grafana on the same machine where InfluxDB is running. Follow the installation instructions for your OS.
3. Start the Grafana service or daemon. The process to start Grafana varies depending on your OS.
4. Access the Grafana web interface through a web browser. By default, it runs on http://localhost:3000.
5. Log in with the default credentials (username: `admin`, password: `admin`).
6. Configure Grafana data source to connect to InfluxDB:
   - Click on the "Configuration" (gear) icon on the left sidebar and select "Data Sources."
   - Click on "Add data source."
   - Choose "InfluxDB" as the data source type.
   - In the HTTP section, set URL to `http://localhost:8086`.
   - In the "InfluxDB Details" section, set the "Database" field to `metrics`.
   - Save the data source configuration.

**Step 4: Set Up MySQL Database**

1. Install MySQL on the machine where you want to run it. Follow the installation instructions for your OS.
2. Create a new database for the Spring Boot application, for example, "demospring_db".
3. Update the `application.properties` file of the Spring Boot application with the correct MySQL database connection details. Replace the placeholders `<DB_USERNAME>`, `<DB_PASSWORD>`, and `<DB_NAME>` with your actual database username, password, and database name, respectively.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/<DB_NAME>?useSSL=false&serverTimezone=UTC
spring.datasource.username=<DB_USERNAME>
spring.datasource.password=<DB_PASSWORD>
```

**Step 5: Build and Deploy Spring Boot Application**

1. Copy the Spring Boot application JAR file (e.g., `demospring-0.0.1-SNAPSHOT.jar`) to the target machine.
2. Open a terminal or command prompt and navigate to the directory where the JAR file is located.
3. Run the Spring Boot application using the following command:

```
java -jar demospring-0.0.1-SNAPSHOT.jar
```

4. The Spring Boot application should start and connect to the MySQL database.

**Step 6: Test the Setup**

1. Ensure that InfluxDB, Telegraf, Grafana, and the Spring Boot application are all running without any errors.
2. Access the Spring Boot application through a web browser to verify that it's up and running (e.g., http://localhost:8080).
3. Check the InfluxDB administration interface to ensure that metrics data is being collected.
4. Access the Grafana web interface and log in with the Grafana credentials.
5. Create a new dashboard in Grafana and add a graph panel to visualize the metrics collected from the Spring Boot application.
