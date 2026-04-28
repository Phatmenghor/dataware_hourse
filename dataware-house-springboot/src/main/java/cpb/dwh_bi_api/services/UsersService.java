package cpb.dwh_bi_api.services;

import cpb.dwh_bi_api.database.PostgrestConnection;
import cpb.dwh_bi_api.models.*;
import cpb.dwh_bi_api.repositories.UsersRepository;
import cpb.dwh_bi_api.result_set.UserResultSet;
import cpb.dwh_bi_api.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {


    private UsersRepository usersRepository;

    private PostgrestConnection postgrestConnection = new PostgrestConnection();

    @Autowired
    private UserResultSet userResultSet;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired(required = false)
    private PasswordEncoder bcryptEncoder;

    public UserModel getProfile(String token) {

        String username = jwtTokenUtil.getUsernameFromToken(token.replace("Bearer ",""));

        String query = usersRepository.findByUsername(username);
        ResultSet result = postgrestConnection.selectAll(query);

        return userResultSet.getProfile(result);
    }

    public List<UserModel> getAll() {

        String query = usersRepository.findAll();

        ResultSet result = postgrestConnection.selectAll(query);

        return userResultSet.getAll(result);
    }

    public UserModel findById(UUID id) {

        String query = usersRepository.findById(id);

        ResultSet result = postgrestConnection.selectAll(query);

        return userResultSet.getOne(result);
    }

    public UserModel findByUsername(String username) {

        String query = usersRepository.findByUsername(username);

        ResultSet result = postgrestConnection.selectAll(query);

        return userResultSet.getOne(result);

    }

    public UserModel findByUsernameAD(String username) {

        String query = usersRepository.findByUsernameAD(username);

        ResultSet result = postgrestConnection.selectAll(query);

        return userResultSet.getOneAD(result);

    }


    public UserModel store(UserModel user) {

        String query = usersRepository.store(user);

        ResultSet result = postgrestConnection.create(query);

        return userResultSet.getOne(result);

    }

    public UserModel update(UserModel user) {

        String query = usersRepository.update(user);

        ResultSet result = postgrestConnection.create(query);

        return userResultSet.getOne(result);

    }

    public UserModel resetPassword(UserModel user) {

        String resetQuery = usersRepository.resetPassword(user);

        ResultSet resultReset = postgrestConnection.query(resetQuery);

        return userResultSet.getOne(resultReset);

    }

    public boolean delete(UserModel user) {

        UserModel userModel = new UserModel();

        String query = usersRepository.findById(user.getId());

        ResultSet result = postgrestConnection.create(query);

        userModel = userResultSet.getOne(result);


        boolean response = false;

        if(userModel.getId() == user.getId()) {

            String deleteQuery = usersRepository.delete(String.valueOf(userModel.getId()));

            postgrestConnection.delete(deleteQuery);
            response = true;
        }

        return response;

    }

}
