package com.example.accessingdatamysql;
//mvn azure-spring-apps:deploy
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping(path="/isb")
@CrossOrigin()
public class MainController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FeedingSpotRepository feedingSpotRepository;

	@Autowired
	private FeedingEventRepository feedingEventRepository;

	@PostMapping(path="/user")
	public @ResponseBody String addNewUser(@RequestBody String user) {
		Gson gson = new Gson();
		User user1 = gson.fromJson(user, User.class);
		userRepository.save(user1);
		return "Saved user";
	}
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
		FeedEvent feedEvent;
		Iterable<FeedingSpot> spots = feedingSpotRepository.findAll();
		spots.forEach( (spot1 -> {
			if(spot.equalsIgnoreCase(spot1.getName()))
				feedingSpotRepository.delete(spot1);
		}));
		feedEvent = new FeedEvent();
		feedEvent.setFeedingSpot(spot);

		feedingEventRepository.save(feedEvent);
		return "Saved";
	}

	@GetMapping(path="/feed/all")
	public @ResponseBody Iterable<FeedEvent> getAllFeedingEvents() {
		return feedingEventRepository.findAll();
	}

	@DeleteMapping(path = "/feed")
	public @ResponseBody String deleteFeedEvent(@RequestBody FeedEvent feedEvent) throws RuntimeException {
		Iterable<FeedEvent> feedingEvents = feedingEventRepository.findAll();

		for (FeedEvent event: feedingEvents) {
			if (Objects.equals(event.getFeedingSpot(), feedEvent.getFeedingSpot())) {
				feedingEventRepository.delete(event);
				return "Deleted";
			}
		}
		throw new RuntimeException("Could not find given feeding event");
	}

	@DeleteMapping(path = "/spot")
	public @ResponseBody String deleteFeedingSpot (@RequestBody String name) {
		Iterable<FeedingSpot> spots = feedingSpotRepository.findAll();

		for (FeedingSpot feedingSpot : spots) {
			if (feedingSpot.getName().equalsIgnoreCase(name)) {
				feedingSpotRepository.delete(feedingSpot);
				return "Deleted";
			}
		}

		throw new RuntimeException("Could not find any spot with given name");
	}


}
