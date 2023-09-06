package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/isb")
public class MainController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FeedingSpotRepository feedingSpotRepository;

	@Autowired
	private FeedingEventRepository feedingEventRepository;

	@PostMapping(path="/spot/add")
	public @ResponseBody String addNewFeedingSpot (@RequestParam String name) {
		FeedingSpot feedingSpot = new FeedingSpot();
		feedingSpot.setName(name);
		feedingSpotRepository.save(feedingSpot);
		return "Saved";
	}

	@GetMapping(path="/spot/all")
	public @ResponseBody Iterable<FeedingSpot> getAllFeedingSpots() {
		return feedingSpotRepository.findAll();
	}

	@PostMapping(path="/feed")
	public @ResponseBody String addNewFeedingEvent (@RequestBody String spot) {
		FeedingEvent feedingEvent = new FeedingEvent();
		Iterable<FeedingSpot> spots = feedingSpotRepository.findAll();
		spots.forEach( (spot1 -> {
			if(spot1.getName().equalsIgnoreCase(spot)){
				feedingEvent.setFeedingSpot(spot1);
			}
		}));
		feedingEventRepository.save(feedingEvent);
		return "Saved";
	}
}
