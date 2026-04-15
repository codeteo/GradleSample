import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction


abstract class ProducerTask : DefaultTask() {

    @get:OutputFile
//    abstract var outputFile: RegularFile
    abstract val outputFile: RegularFileProperty

    @TaskAction
    fun producer() {
        val foo = outputFile.asFile.get().createNewFile()
        println("Producer is running... file is created: $foo")
    }
}


abstract class ConsumerTask : DefaultTask() {

    @get:Input
//    abstract var result: String
    abstract val result: Property<String>

    @TaskAction
    fun consumer() {
        println("Consumer is doing something with the result: ${result.get()}")
    }
}