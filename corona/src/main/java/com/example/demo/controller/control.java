package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.coronaState;
import com.example.demo.service.coronaService;

@Controller
public class control {

	
	@Autowired
	private coronaService service;
	
	@RequestMapping("/")
	public String getData(Model model) {
		List<coronaState> states= service.getList();
		int sumTotalCases=states.stream().mapToInt(state -> state.getTotalConfirmCases()).sum();
		int diffrence=states.stream().mapToInt(state -> state.getDelta()).sum();
		model.addAttribute("Delta", diffrence);
		model.addAttribute("coronaData", states);
		model.addAttribute("totalCases", sumTotalCases);
		return "show";
	}
	
}
