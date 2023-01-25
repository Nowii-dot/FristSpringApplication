package pl.air.sebastian.nowak.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Movie> getAll(){
       return jdbcTemplate.query("SELECT * FROM movie", BeanPropertyRowMapper.newInstance(Movie.class));
    }

    public Movie getById(int id){
       return jdbcTemplate.queryForObject("SELECT id, title, rating FROM movie WHERE id = ?", BeanPropertyRowMapper.newInstance(Movie.class), id);
    }

    public int save(List<Movie> movies) {

        movies.forEach(movie -> jdbcTemplate
                .update("insert into movie(title, rating) values(? , ?)",
                        movie.getTitle(), movie.getRating()
                ));

        return 1;
    }
    public int delete(int  id){
        return jdbcTemplate.update("Delete FROM movie where id=?", id);
    }

    public int update(Movie movie){
        return  jdbcTemplate.update("UPDATE movie set title=?, rating=? where id=?;", movie.getTitle(), movie.getRating(), movie.getId());
    }

}
