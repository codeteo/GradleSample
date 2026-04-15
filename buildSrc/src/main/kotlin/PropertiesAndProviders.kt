import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFile
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction


abstract class ProducerTask : DefaultTask() {

    @get:OutputFile
    abstract var outputFile: RegularFile

    @TaskAction
    fun producer() {
        println("Producer is running...")
    }
}


abstract class ConsumerTask : DefaultTask() {

    @get:Input
    abstract var result: String

    @TaskAction
    fun consumer() {
        println("Consumer is doing something with the result: $result")
    }
}