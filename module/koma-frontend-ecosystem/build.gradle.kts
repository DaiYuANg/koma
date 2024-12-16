group = "org.koma"

dependencies {
  implementation("com.caoccao.javet:javet:4.1.1")
  implementation("com.caoccao.javet:javet-node-linux-arm64:4.1.1")
  implementation("com.caoccao.javet:javet-node-linux-x86_64:4.1.1")
  implementation("com.caoccao.javet:javet-node-macos-arm64:4.1.1")
  implementation("com.caoccao.javet:javet-node-macos-x86_64:4.1.1")
  implementation("com.caoccao.javet:javet-node-windows-x86_64:4.1.1")
  implementation("com.caoccao.javet:javet-v8-linux-arm64:4.1.1")
  implementation("com.caoccao.javet:javet-v8-linux-x86_64:4.1.1")
  implementation("com.caoccao.javet:javet-v8-macos-arm64:4.1.1")
  implementation("com.caoccao.javet:javet-v8-macos-x86_64:4.1.1")
  implementation("com.caoccao.javet:javet-v8-windows-x86_64:4.1.1")
  testImplementation(kotlin("test"))
}
