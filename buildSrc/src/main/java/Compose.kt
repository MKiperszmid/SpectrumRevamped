object Compose {
    const val version = "1.1.1"

    const val ui = "androidx.compose.ui:ui:$version"
    const val material = "androidx.compose.material:material:$version"
    const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
    const val tooling = "androidx.compose.ui:ui-tooling:$version"

    const val runtime = "androidx.compose.runtime:runtime:$version"
    const val compiler = "androidx.compose.compiler:compiler:$version"
    const val icons = "androidx.compose.material:material-icons-extended:$version"

    private const val activity_compose_version = "1.4.0"
    const val activityCompose = "androidx.activity:activity-compose:$activity_compose_version"

    private const val lifecycleVersion = "2.4.1"
    const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"

    private const val navigationVersion = "2.4.2"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val hiltNavigationComposeVersion = "1.0.0"
    const val hiltNavigationCompose =
        "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"
}