package javademo.controller;

import java.util.List;
import java.util.Optional;
import javademo.modal.Movie;
import javademo.request.FilterRequest;
import javademo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MovieController {

  private final MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @RequestMapping(value = "/movies", method = RequestMethod.GET)
  public List<Movie> getAllMovies() {
    return movieService.getMovies();
  }

  //TODO Correctly handle errors
  @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
  public Optional<Movie> getMovieById(@PathVariable long id) {
    Optional<Movie> movie = movieService.findMovieById(id);
    if (movie.isPresent()) {
      return movieService.findMovieById(id);
    }
    return movie;
  }

  @RequestMapping(value = "/movies/search/title", method = RequestMethod.GET)
  public List<Movie> searchByTitle(@RequestParam(value = "title") String subString) {
    return movieService.filterByTitle(subString);
  }

  @RequestMapping(value = "/movies/search/actor", method = RequestMethod.GET)
  public List<Movie> searchByActor(@RequestParam(value = "actor") String actor) {
    return movieService.filterByActor(actor);
  }

  @RequestMapping(value = "/movies/search/director", method = RequestMethod.GET)
  public List<Movie> searchByDirector(@RequestParam(value = "director") String director) {
    return movieService.filterByDirector(director);
  }

  @RequestMapping(value = "/movies/filter", method = RequestMethod.POST)
  public List<Movie> filterMovies(@RequestBody FilterRequest request) {
    return movieService.filterMoviesByCriteria(request);
  }
}
