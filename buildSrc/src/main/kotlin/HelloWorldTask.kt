import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class HelloWorldTask : DefaultTask() {

    @get:Input
    var greeting: String = "Hello from buildSrc!"

    @TaskAction
    fun greet() {
        println(greeting)
    }
}