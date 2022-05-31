plugins {
    id("dagger.hilt.android.plugin")
}

apply {
    from("$rootDir/ui-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUI))
    "implementation"(project(Modules.Player.domain))
    "implementation"(ExoPlayer.player)
}