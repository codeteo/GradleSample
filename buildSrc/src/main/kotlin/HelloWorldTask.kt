import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class HelloWorldTask : DefaultTask() {

    @get:Input
    abstract val greeting: Property<String>

//    @Input
//    val greeting: String = "Hello, World!"

    @TaskAction
    fun greet() {
        println(greeting.get())
    }
}