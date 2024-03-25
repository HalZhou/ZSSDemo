object VersionKotlin {

    private val version = "1.8.0"

    val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"

    val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"

    val stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"

    val stblibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"

    //协程
    object Coroutines {
        private const val version = "1.7.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }
}