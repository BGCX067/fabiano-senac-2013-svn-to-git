package br.com.spring.curso.controller;

import java.text.ParseException;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class Controller {

	Logger logger = Logger.getLogger(Controller.class);

	@RequestMapping("/alo")
	public ModelAndView helloWorld() {
		String minhaMensagem = "Alo Mundo, Spring 3.0!" + System.currentTimeMillis();
		return new ModelAndView("alo", "mensagem", minhaMensagem);
	}

	@RequestMapping(value = "/calculaidade/{dia}/{mes}/{ano}", method = RequestMethod.GET)
	public ModelAndView calcula(@PathVariable String dia, @PathVariable String mes, @PathVariable String ano) throws ParseException {

		String resultadoValidacao = validaDadosRecebidos(dia, mes, ano);

		if (StringUtils.isBlank(resultadoValidacao)) {

			resultadoValidacao = calculaIdade(dia, mes, ano);

			logger.info("Idade =" + resultadoValidacao);
		}

		return new ModelAndView("idade", "resultado", resultadoValidacao);

	}

	private String validaDadosRecebidos(String dia, String mes, String ano) {

		String resultado = "";

		if (!StringUtils.isNumeric(dia)) {
			resultado = " - Digite um Dia Válido <br />";
		}

		if (!StringUtils.isNumeric(mes)) {
			resultado += " - Digite um Mes Válido <br />";
		}
		if (!StringUtils.isNumeric(ano)) {
			resultado += " - Digite um Ano Válido <br />";
		}

		if (!resultado.isEmpty()) {
			logger.error("Erro =" + resultado);
		}

		return resultado;
	}

	private String calculaIdade(String dia, String mes, String ano) {

		String resultado = "";

		try {
			Calendar hoje = Calendar.getInstance();
			Calendar data = Calendar.getInstance();

			data.setLenient(false);

			data.set(converteParaInteger(ano), converteParaInteger(mes), converteParaInteger(dia));

			int idade = hoje.get(Calendar.YEAR) - data.get(Calendar.YEAR);

			data.add(Calendar.YEAR, idade);

			if (hoje.before(data)) {
				idade--;
			}

			if (!resultado.isEmpty()) {
				logger.info("Erro =" + resultado);
			}

			resultado = "Sua idade é ===>" + idade;
		} catch (Exception e) {

			resultado = "Data informada é inválida Corrija e tente novamente!";

		}

		return resultado;
	}

	private int converteParaInteger(String numero) {

		return Integer.valueOf(numero);
	}
}