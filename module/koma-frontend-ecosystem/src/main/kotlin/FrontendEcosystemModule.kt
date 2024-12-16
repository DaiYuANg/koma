import com.caoccao.javet.interop.V8Host
import com.caoccao.javet.interop.V8Runtime

//TODO process download package manager

class FrontendEcosystemModule {
  init {
    val v8Runtime: V8Runtime = V8Host.getNodeInstance().createV8Runtime()
  }
}