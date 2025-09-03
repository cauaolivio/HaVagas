package br.edu.ifsp.scl.prmd.sc3039587.havagas

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.prmd.sc3039587.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        // Configura o botão Limpar
        activityMainBinding.btnLimpar.setOnClickListener {
            limparFormulario()
        }
    }

    private fun limparFormulario() {
        with(activityMainBinding) {
            editNome.text?.clear()
            editEmail.text?.clear()
            switchEmailAtualizacoes.isChecked = false
            editTelefone.text?.clear()
            radioTelefoneComercial.isChecked = true
            switchAdicionarCelular.isChecked = false
            radioSexoMasculino.isChecked = true
            editDataNascimento.text?.clear()
            editVagasInteresse.text?.clear()
            spinnerFormacao.setSelection(0)
            esconderCamposFormacao()
        }
    }

    private fun esconderCamposFormacao() {
        activityMainBinding.textAnoFormatura.visibility = View.GONE
        activityMainBinding.editAnoFormatura.visibility = View.GONE
        activityMainBinding.textAnoConclusao.visibility = View.GONE
        activityMainBinding.editAnoConclusao.visibility = View.GONE
        activityMainBinding.textInstituicao.visibility = View.GONE
        activityMainBinding.editInstituicao.visibility = View.GONE
        activityMainBinding.textTituloMonografia.visibility = View.GONE
        activityMainBinding.editTituloMonografia.visibility = View.GONE
        activityMainBinding.textOrientador.visibility = View.GONE
        activityMainBinding.editOrientador.visibility = View.GONE

        activityMainBinding.editAnoFormatura.text?.clear()
        activityMainBinding.editAnoConclusao.text?.clear()
        activityMainBinding.editInstituicao.text?.clear()
        activityMainBinding.editTituloMonografia.text?.clear()
        activityMainBinding.editOrientador.text?.clear()
    }
}