apply {
    from("$rootDir/ui-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.Home.domain))
}