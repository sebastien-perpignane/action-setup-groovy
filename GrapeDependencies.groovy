import groovy.cli.Option
import groovy.cli.Unparsed
import groovy.cli.commons.CliBuilder
import groovy.grape.Grape
import org.codehaus.groovy.control.CompilerConfiguration

import java.nio.file.Paths

class GrapeDependencies {

    static void main(String[] args) {

        CliBuilder cliBuilder = new CliBuilder(usage: "grape-dependencies")

        CliOptions options = new CliOptions()
        cliBuilder.parseFromInstance(options, args)

        def depsAsStr = options.scripts.collectMany {String scriptPath ->

            String scriptParentPath = Paths.get(scriptPath).toAbsolutePath().getParent().toString()
            CompilerConfiguration compilerConfiguration = new CompilerConfiguration()
            compilerConfiguration.setClasspath(scriptParentPath)

            GroovyShell shell = new GroovyShell(compilerConfiguration)

            shell.parse(new File(scriptPath))
            Grape.listDependencies(shell.classLoader).collect { dep ->
                dep.collect {entry -> "$entry.key:$entry.value"}.join("|")
            }
        }.sort()

        File output = new File("${options.getOutputPath()}.groovy.dependencies")
        output.withWriter {writer ->
            depsAsStr.each {writer.println it}
        }

        println depsAsStr
    }

}

class CliOptions {

    @Option(shortName = "o", longName = "output-path", description = "Where the dependency file will be written. Default value is the working dir of the JVM process", optionalArg = false)
    String outputPath

    @Unparsed
    List<String> scripts

    String getOutputPath() {
        String lOutPath = Paths.get("").toAbsolutePath()
        if (outputPath) {
            lOutPath = !outputPath.endsWith("/") ? outputPath + "/" : outputPath
            println "dependency file will be exported to custom dir: $lOutPath"
        }
        else {
            println "dependency file will be exported to working dir (default): $lOutPath"
        }

        return lOutPath
    }

}
