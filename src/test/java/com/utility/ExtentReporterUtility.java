package com.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtility {

private static ExtentReports extentReports;
private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
private static Path reportPath;

public static void makeReportSelfContained(Path targetPath) {
if (targetPath == null) {
return;
}
try {
String html = Files.readString(targetPath, StandardCharsets.UTF_8);
String css = loadResource("/com/aventstack/extentreports/offline/spark/css/spark-style.css");
String fontAwesomeCss = loadResource("/com/aventstack/extentreports/offline/commons/css/icons/font-awesome.min.css");
String jsontreeJs = loadResource("/com/aventstack/extentreports/offline/commons/js/jsontree.js");
String sparkScriptJs = loadResource("/com/aventstack/extentreports/offline/spark/js/spark-script.js");
String logoBase64 = toBase64DataUrl("/com/aventstack/extentreports/offline/commons/img/logo.png", "image/png");

html = html.replace("<link rel=\"stylesheet\" href=\"spark/spark-style.css\">", "<style>" + css + "</style>");
html = html.replace("<link rel=\"stylesheet\" href=\"spark/font-awesome.min.css\">", "<style>" + fontAwesomeCss + "</style>");
html = html.replace("<script src=\"spark/jsontree.js\"></script>", "<script>" + jsontreeJs + "</script>");
html = html.replace("<script src=\"spark/spark-script.js\"></script>", "<script>" + sparkScriptJs + "</script>");
html = html.replace("<link rel=\"apple-touch-icon\" href=\"spark/logo.png\">", "<link rel=\"apple-touch-icon\" href=\"" + logoBase64 + "\">");
html = html.replace("<link rel=\"shortcut icon\" href=\"spark/logo.png\">", "<link rel=\"shortcut icon\" href=\"" + logoBase64 + "\">");
html = html.replace("spark/logo.png", logoBase64);
html = html.replace("https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css", "");
html = html.replace("href=\"spark/logo.png\"", "href=\"" + logoBase64 + "\"");
html = html.replace("url('spark/logo.png')", "url('" + logoBase64 + "')");
html = html.replace("url(\"spark/logo.png\")", "url(\"" + logoBase64 + "\")");
Files.writeString(targetPath, html, StandardCharsets.UTF_8);
} catch (IOException e) {
throw new RuntimeException("Unable to make report self-contained", e);
}
}

public static void setupSparkReporter(String reportName) {
String reportPathValue = reportName;
if (!reportPathValue.contains("\\") && !reportPathValue.contains("/")) {
reportPathValue = System.getProperty("user.dir") + "//" + reportPathValue;
}
reportPath = Paths.get(reportPathValue).toAbsolutePath().normalize();
if (reportPath.getParent() != null) {
try {
Files.createDirectories(reportPath.getParent());
} catch (IOException e) {
throw new RuntimeException("Unable to create report directory", e);
}
}

ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath.toString());
extentSparkReporter.config().enableOfflineMode(true);
extentSparkReporter.config().setResourceCDN("local");
extentSparkReporter.config().thumbnailForBase64(true);

extentReports = new ExtentReports();
extentReports.attachReporter(extentSparkReporter);
}

public static void createExtentTest(String testName) {
ExtentTest test = extentReports.createTest(testName);
extentTest.set(test);
}

public static ExtentTest getTest() {
return extentTest.get();
}

public static void flushReport() {
extentReports.flush();
makeReportSelfContained();
}

private static void makeReportSelfContained() {
if (reportPath == null) {
return;
}
makeReportSelfContained(reportPath);
}

private static String loadResource(String resourcePath) throws IOException {
try (InputStream inputStream = ExtentReporterUtility.class.getResourceAsStream(resourcePath)) {
if (inputStream == null) {
throw new IOException("Resource not found: " + resourcePath);
}
return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
}
}

private static String toBase64DataUrl(String resourcePath, String mimeType) throws IOException {
try (InputStream inputStream = ExtentReporterUtility.class.getResourceAsStream(resourcePath)) {
if (inputStream == null) {
throw new IOException("Resource not found: " + resourcePath);
}
String encoded = Base64.getEncoder().encodeToString(inputStream.readAllBytes());
return "data:" + mimeType + ";base64," + encoded;
}
}
}
