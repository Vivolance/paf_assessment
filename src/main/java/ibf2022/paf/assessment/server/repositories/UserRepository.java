package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.DeserUtils;
import ibf2022.paf.assessment.server.models.TaskUpdateException;
import ibf2022.paf.assessment.server.models.User;

// TODO: Task 3

@Repository
public class UserRepository {

    public static final String SQL_FIND_USER_BY_NAME = """
		select user_id, username, name from users where username = ?
		""";

    public static final String SQL_INSERT_USER = "insert into users (user_id, username, name) values (?, ?, ?)";

    @Autowired
    private JdbcTemplate template;

    public Optional<User> findUserByUsername(String username) {
        SqlRowSet rs = template.queryForRowSet(SQL_FIND_USER_BY_NAME, username);

        if (rs.next()) {
            // create a user object
            User user = DeserUtils.toUser(rs);
            // populate the user object with data from result set
            return Optional.of(user);
        } else {
            return Optional.empty();
        }

    }

    public String insertUser(User user) throws TaskUpdateException {
        int rowsInserted = template.update(SQL_INSERT_USER, user.getUserId(), user.getUsername(), user.getName());
        // check if user already exists
        if (rowsInserted > 0) {
            return user.getUserId();
        } else {
            // if user is not inserted successfully, throw a TaskUpdateException
            throw new TaskUpdateException();
        }

    }
}