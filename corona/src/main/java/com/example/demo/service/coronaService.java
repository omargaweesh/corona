package com.example.demo.service;




import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.model.coronaState;

@Service
public class coronaService {

	private static String 	URI_CORONA="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	
	private List<coronaState> list=new ArrayList<>();
	
	
	public List<coronaState> getList() {
		return list;
	}



	@PostConstruct
	@Scheduled(cron = "* * * 1 * *")
	public void coronaservice() throws IOException, InterruptedException {
		List<coronaState> newList=new ArrayList<>();
		HttpClient client=HttpClient.newHttpClient();
		HttpRequest request=HttpRequest.newBuilder().
				uri(URI.create(URI_CORONA)).build();
		
		HttpResponse<String> Response=client.send(request, HttpResponse.BodyHandlers.ofString());
	
		
		
		StringReader in=new StringReader(Response.body());
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {
			coronaState st=new coronaState();
			st.setCity(record.get("Province/State"));
		    st.setCountry(record.get("Country/Region")); 
		    int lastday=Integer.parseInt(record.get(record.size()-1));
		    int dayBefore=Integer.parseInt(record.get(record.size()-2));
		    st.setTotalConfirmCases(lastday);
		    st.setDelta(lastday - dayBefore);
		   newList.add(st);
		}
	
		list=newList;
	}
}
