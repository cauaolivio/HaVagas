package br.edu.ifsp.scl.prmd.sc3039587.havagas

import android.app.AlertDialog
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

        // Configura os botões de Salvar e Limpar
        activityMainBinding.btnSalvar.setOnClickListener {
            mostrarDadosEmPopUp()
        }

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

    private fun mostrarDadosEmPopUp() {
        val mensagem = StringBuilder()

        // Coleta os dados dos campos
        val nome = activityMainBinding.editNome.text.toString()
        val email = activityMainBinding.editEmail.text.toString()
        val recebeEmails = activityMainBinding.switchEmailAtualizacoes.isChecked
        val telefone = activityMainBinding.editTelefone.text.toString()
        val tipoTelefone = if (activityMainBinding.radioTelefoneComercial.isChecked) "Comercial" else "Residencial"
        val celular = if (activityMainBinding.switchAdicionarCelular.isChecked) activityMainBinding.editCelular.text.toString() else "Não informado"
        val sexo = if (activityMainBinding.radioSexoMasculino.isChecked) "Masculino" else "Feminino"
        val dataNascimento = activityMainBinding.editDataNascimento.text.toString()
        val formacao = activityMainBinding.spinnerFormacao.selectedItem.toString()
        val vagasInteresse = activityMainBinding.editVagasInteresse.text.toString()

        // Adiciona os dados à mensagem
        mensagem.appendLine("Nome Completo: $nome")
        mensagem.appendLine("E-mail: $email")
        mensagem.appendLine("Receber E-mails: ${if(recebeEmails) "Sim" else "Não"}")
        mensagem.appendLine("Telefone: $telefone ($tipoTelefone)")
        if (celular != "Não informado") {
            mensagem.appendLine("Celular: $celular")
        }
        mensagem.appendLine("Sexo: $sexo")
        mensagem.appendLine("Data de Nascimento: $dataNascimento")
        mensagem.appendLine("Formação: $formacao")

        // Adiciona os dados de formação específicos
        when (activityMainBinding.spinnerFormacao.selectedItemPosition) {
            0, 1 -> {
                mensagem.appendLine("Ano de Formatura: ${activityMainBinding.editAnoFormatura.text}")
            }
            2, 3 -> {
                mensagem.appendLine("Ano de Conclusão: ${activityMainBinding.editAnoConclusao.text}")
                mensagem.appendLine("Instituição: ${activityMainBinding.editInstituicao.text}")
            }
            4, 5 -> {
                mensagem.appendLine("Ano de Conclusão: ${activityMainBinding.editAnoConclusao.text}")
                mensagem.appendLine("Instituição: ${activityMainBinding.editInstituicao.text}")
                mensagem.appendLine("Título da Monografia: ${activityMainBinding.editTituloMonografia.text}")
                mensagem.appendLine("Orientador: ${activityMainBinding.editOrientador.text}")
            }
        }

        mensagem.appendLine("Vagas de Interesse: ${vagasInteresse.ifEmpty { "Não informado" }}")

        // Exibe o pop-up
        AlertDialog.Builder(this)
            .setTitle("Dados do Cadastro")
            .setMessage(mensagem.toString())
            .setPositiveButton("OK", null)
            .show()
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