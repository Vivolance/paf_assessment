package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import java.util.UUID;

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

    public static final String SQL_INSERT_USER = "insert into users (user_id, username, name) values (?, ?, ?)";

    @Autowired
    private JdbcTemplate template;

    public Optional<User> findUserByUsername(String username) {
        SqlRowSet rs = template.queryForRowSet(SQL_FIND_USER_BY_NAME, username);

        if (rs.next()) {
            // create a user object
            User user = new User();
            // populate the user object with data from result set
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
        
    }

    public String insertUser(User user) {

        String userId = UUID.randomUUID().toString().substring(0, 8);
        
        int rowsInserted = template.update(SQL_INSERT_USER, userId, user.getUsername(), user.getName());
        // check if user already exists
        if (rowsInserted > 0) {
            return userId;
        } else {
            // if user is not inserted successfully
            return null;
        }

    }
}
