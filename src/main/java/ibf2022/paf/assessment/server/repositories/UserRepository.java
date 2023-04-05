package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.User;

// TODO: Task 3

@Repository
public class UserRepository {

    public static final String SQL_FIND_USER_BY_NAME = """
		select * from users where username = ?
		""";

    @Autowired
    private JdbcTemplate template;

    public Optional<User> findUserByUsername(String username) {
        SqlRowSet rs = template.queryForRowSet(SQL_FIND_USER_BY_NAME, username);

        return null;
        
    }

    public String insertUser(User user) {

        return null;
    }
}
