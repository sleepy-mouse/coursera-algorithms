package gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

import java.nio.file.Files
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

/**
 * @author Chris Qiu
 */
class PrepareTask extends DefaultTask {
    @Input
    String assignmentName

    @TaskAction
    def prepare() {
        println 'Prepare assignment zip file to be uploaded to Coursera.'
        Assignment assignment = Assignment.valueOf(assignmentName)
        def srcPath = assignment.getSrcPath(project.projectDir)
        List<File> deliverables = process(srcPath, assignment)
        File zip = compress(assignment.zipFileName, project.buildDir, deliverables)
        (0..150).each { print '*' }
        println ""
        println "Submission:"
        println "$zip.absolutePath"
    }

    private static File compress(String zipFileName, File outputDir, List<File> deliverables) {
        def zip = new File(outputDir, zipFileName)
        ZipOutputStream zipOS = new ZipOutputStream(new FileOutputStream(zip))
        deliverables.each { f ->
            zipOS.putNextEntry(new ZipEntry(f.name))
            f.withInputStream { InputStream is ->
                Files.copy(is, zipOS)
            }
            zipOS.closeEntry()
        }
        zipOS.close()
        zip
    }

    private List<File> process(GString srcPath, assignment) {
        new File(srcPath).listFiles().findAll {
            assignment.files.contains(it.name)
        }.collect {
            List<String> lines = it.readLines().findAll { !it.trim().startsWith('package') }
            String pathString = "${project.buildDir}/${assignment.packagePath}"
            def targetPath = new File(pathString)
            if (!targetPath.exists())
                targetPath.mkdirs()
            File f = new File(targetPath, it.name)
            f.withWriter { w ->
                lines.each { w.writeLine(it) }
            }
            f
        }
    }

    enum Assignment {
        PART1_WEEK1('part1.week1', 'percolation-submit.zip', 'Percolation.java', 'PercolationStats.java'),

        private final String packageName
        private final String packagePath
        private final String zipFileName
        private final List<String> files

        Assignment(String packageName, String zipFileName, String... files) {
            this.packageName = packageName
            this.packagePath = packageName.replace('.', '/')
            this.zipFileName = zipFileName
            this.files = files.toUnique().toList()
        }

        GString getSrcPath(File projectDir) {
            "${projectDir.toString()}/src/main/java/${packagePath}"
        }
    }
}
