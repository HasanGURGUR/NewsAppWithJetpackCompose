plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
    id("com.google.dagger.hilt.android") version "2.51" apply false
    id("com.google.devtools.ksp") version "1.9.23-1.0.19" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
