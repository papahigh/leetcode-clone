import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.net.*;
import java.lang.management.*;
import java.util.concurrent.*;

public class SandboxInspector {

    public static void main(String[] args) throws Exception {
        System.out.println("=== Sandbox Troubleshooting and Security Assessment ===");

        // User/Group/PID Info
        printUserGroupPidInfo();

        // Top-Level Directories and Writable Files
        printDirectoriesAndWritableFiles();

        // Linux Capabilities
        printLinuxCapabilities();

        // Network Interfaces
        printNetworkInterfaces();

        // Running Processes
        printRunningProcesses();

        // Additional Security-Related Checks
        printAdditionalSecurityChecks();
    }

    /**
     * Prints current user UID, GID, and PID.
     */
    private static void printUserGroupPidInfo() {
        System.out.println("\n--- User/Group/PID Info ---");
        try {
            String userId = System.getProperty("user.name");
            String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
            System.out.println("User ID (UID): " + userId);
            System.out.println("Process ID (PID): " + pid);

            // Getting GID - Using shell execution
            try {
                ProcessBuilder pb = new ProcessBuilder("id", "-g");
                Process process = pb.start();
                process.waitFor();
                String gid = new String(process.getInputStream().readAllBytes());
                System.out.println("Group ID (GID): " + gid.trim());
            } catch (Exception e) {
                System.out.println("Unable to retrieve Group ID: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Failed to fetch user/group/pid info: " + e.getMessage());
        }
    }

    /**
     * Prints top-level directories and lists writable files within those directories.
     */
    private static void printDirectoriesAndWritableFiles() {
        System.out.println("\n--- Top-Level Directories and Writable Files ---");

        try {
            File[] roots = File.listRoots();
            for (File root : roots) {
                System.out.println("Top-Level Directory: " + root);
            }

            // Find writable files
            System.out.println("\nWritable Files:");
            Files.walk(Paths.get("/"))
                    .filter(Files::isWritable)
                    .limit(50) // Limit to avoid overwhelming output
                    .forEach(path -> System.out.println(path.toString()));

        } catch (Exception e) {
            System.err.println("Error reading directories or writable files: " + e.getMessage());
        }
    }

    /**
     * Prints Linux capabilities (requires "capsh" or equivalent to fetch capabilities).
     */
    private static void printLinuxCapabilities() {
        System.out.println("\n--- Linux Capabilities ---");

        try {
            ProcessBuilder pb = new ProcessBuilder("capsh", "--print");
            Process process = pb.start();
            process.waitFor();
            String capabilities = new String(process.getInputStream().readAllBytes());
            System.out.println(capabilities);
        } catch (Exception e) {
            System.err.println("Error fetching Linux capabilities (is capsh installed?): " + e.getMessage());
        }
    }

    /**
     * Prints all available network interfaces.
     */
    private static void printNetworkInterfaces() {
        System.out.println("\n--- Network Interfaces ---");

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface netInterface = interfaces.nextElement();
                System.out.println("Interface: " + netInterface.getName());
                System.out.println("  Display Name: " + netInterface.getDisplayName());
                System.out.println("  Addresses:");
                Collections.list(netInterface.getInetAddresses())
                        .forEach(addr -> System.out.println("    - " + addr.getHostAddress()));
                System.out.println("  MTU: " + netInterface.getMTU());
                System.out.println("  Up: " + netInterface.isUp());
                System.out.println("  Loopback: " + netInterface.isLoopback());
                System.out.println("  Virtual: " + netInterface.isVirtual());
            }
        } catch (Exception e) {
            System.err.println("Error retrieving network interfaces: " + e.getMessage());
        }
    }

    /**
     * Prints the list of all running processes.
     */
    private static void printRunningProcesses() {
        System.out.println("\n--- Running Processes ---");
        try {
            ProcessBuilder pb = new ProcessBuilder("ps", "-e");
            Process process = pb.start();
            process.waitFor();
            String processList = new String(process.getInputStream().readAllBytes());
            System.out.print(processList);
        } catch (Exception e) {
            System.err.println("Error retrieving running processes: " + e.getMessage());
        }
    }

    /**
     * Prints additional sandbox-related security checks like environment variables and restricted resources.
     */
    private static void printAdditionalSecurityChecks() {
        System.out.println("\n--- Additional Security Checks ---");

        // Environment Variables
        System.out.println("\nEnvironment Variables:");
        System.getenv().forEach((key, value) -> System.out.println(key + " = " + value));

        // System Properties
        System.out.println("\nSystem Properties:");
        System.getProperties().forEach((key, value) -> System.out.println(key + " = " + value));

        // Check for restricted resources
        try {
            System.out.println("\nAttempting to access restricted resources (e.g., /etc/shadow)...");
            Path restrictedFile = Paths.get("/etc/shadow");
            if (Files.exists(restrictedFile)) {
                System.out.println("/etc/shadow exists. Readable: " + Files.isReadable(restrictedFile));
            } else {
                System.out.println("/etc/shadow not accessible.");
            }
        } catch (Exception e) {
            System.out.println("Error while checking restricted resources: " + e.getMessage());
        }
    }
}
