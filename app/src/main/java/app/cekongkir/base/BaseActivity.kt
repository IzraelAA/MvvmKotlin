package app.cekongkir.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {

    private var _binding: ViewBinding? = null

    abstract val bindingInflater: (LayoutInflater) -> B

    @Suppress("UNCHECKED_CAST")
    protected val binding: B
        get() = _binding as B

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        setup()
    }

    abstract fun setup()


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}