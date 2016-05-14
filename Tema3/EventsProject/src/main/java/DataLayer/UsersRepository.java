package DataLayer;

import java.sql.SQLException;

import DomainModel.User;

public interface UsersRepository {
	public User getUser(String user) throws SQLException;
	public boolean insertUser();
}
