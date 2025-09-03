package br.edu.ifsp.scl.prmd.sc3039587.havagas

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.prmd.sc3039587.havagas.databinding.ActivityMainBinding
import java.util.Calendar
class MainActivity : AppCompatActivity() {

    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        // Configura o Spinner de Formação
        configurarSpinnerFormacao()

        // Configura a visibilidade do campo de celular
        activityMainBinding.switchAdicionarCelular.setOnCheckedChangeListener { _, isChecked ->
            activityMainBinding.editCelular.visibility = if (isChecked) View.VISIBLE else View.GONE
            if (!isChecked) {
                activityMainBinding.editCelular.text?.clear()
            }
        }

        // Configura o DatePicker para o campo de data de nascimento
        activityMainBinding.editDataNascimento.setOnClickListener {
            mostrarDatePicker()
        }

        // Configura o botão Limpar
        activityMainBinding.btnLimpar.setOnClickListener {
            limparFormulario()
        }
    }

    private fun configurarSpinnerFormacao() {
        val formacoes = resources.getStringArray(R.array.formacao_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, formacoes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        activityMainBinding.spinnerFormacao.adapter = adapter

        activityMainBinding.spinnerFormacao.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                esconderCamposFormacao()

                when (position) {
                    0, 1 -> {
                        activityMainBinding.textAnoFormatura.visibility = View.VISIBLE
                        activityMainBinding.editAnoFormatura.visibility = View.VISIBLE
                    }
                    2, 3 -> {
                        activityMainBinding.textAnoConclusao.visibility = View.VISIBLE
                        activityMainBinding.editAnoConclusao.visibility = View.VISIBLE
                        activityMainBinding.textInstituicao.visibility = View.VISIBLE
                        activityMainBinding.editInstituicao.visibility = View.VISIBLE
                    }
                    4, 5 -> {
                        activityMainBinding.textAnoConclusao.visibility = View.VISIBLE
                        activityMainBinding.editAnoConclusao.visibility = View.VISIBLE
                        activityMainBinding.textInstituicao.visibility = View.VISIBLE
                        activityMainBinding.editInstituicao.visibility = View.VISIBLE
                        activityMainBinding.textTituloMonografia.visibility = View.VISIBLE
                        activityMainBinding.editTituloMonografia.visibility = View.VISIBLE
                        activityMainBinding.textOrientador.visibility = View.VISIBLE
                        activityMainBinding.editOrientador.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                esconderCamposFormacao()
            }
        }
    }

    private fun mostrarDatePicker() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val dataSelecionada = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year)
                activityMainBinding.editDataNascimento.setText(dataSelecionada)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
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
}