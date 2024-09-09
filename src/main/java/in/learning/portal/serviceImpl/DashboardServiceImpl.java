package in.learning.portal.serviceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.learning.portal.dto.QuoteApiResponseDto;
import in.learning.portal.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	private String url = "https://dummyjson.com/quotes/random";

	@Override
	public QuoteApiResponseDto getQuote() {

		RestTemplate template = new RestTemplate();

		ResponseEntity<QuoteApiResponseDto> forEntity = template.getForEntity(url, QuoteApiResponseDto.class);
		QuoteApiResponseDto body = forEntity.getBody();
		System.out.println("Quote ID: " + body.getId());
	    System.out.println("Author: " + body.getAuthor());
	    System.out.println("Quote :" + body.getQuote());
		return body;

	}

}
	