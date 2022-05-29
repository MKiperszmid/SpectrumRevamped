object Testing {
    private const val junit_version = "4.13.2"
    const val junit = "junit:junit:$junit_version"

    private const val ext_junit_version = "1.1.2"
    const val extJunit = "androidx.test.ext:junit:$ext_junit_version"

    private const val espresso_version = "3.4.0"
    const val espresso = "androidx.test.espresso:espresso-core:$espresso_version"

    const val junitUi = "androidx.compose.ui:ui-test-junit4:${Compose.version}"
    const val compose = "androidx.compose.ui:ui-test:${Compose.version}"
}