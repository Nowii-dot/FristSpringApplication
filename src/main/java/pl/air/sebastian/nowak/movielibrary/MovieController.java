package pl.air.sebastian.nowak.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/test")
    public int test(){
        return 1+1;
    }

    @GetMapping("")
    public List<Movie> getAll(){
        return movieRepository.getAll();
    }

    @GetMapping("{id}")
    public Movie getById(@PathVariable("id") int id) {
        return movieRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Movie> movies) {
        return movieRepository.save(movies);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id){
        return movieRepository.delete(id);

    }
    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Movie updateMovie){
            Movie movie = movieRepository.getById(id);
        if(movie!=null)
        {
            movie.setTitle(updateMovie.getTitle());
            movie.setRating(updateMovie.getRating());
            movieRepository.update(movie);
            return 1;
        }else {
            return -1;
        }

    }
    @PatchMapping("/{}id")
    public int updateOne(@PathVariable("id")int id, @RequestBody Movie updateMovie){
        Movie movie = movieRepository.getById(id);
        if(movie!=null)
        {
            if(updateMovie.getTitle() !=null) movie.setTitle(updateMovie.getTitle());
            if(updateMovie.getRating() >0)  movie.setRating(updateMovie.getRating());

            movieRepository.update(movie);
            return 1;
        }else {
            return -1;
        }
    }




}
