package cpb.dwh_bi_api.services;

import cpb.dwh_bi_api.database.PostgrestConnection;
import cpb.dwh_bi_api.models.JwtUserModel;
import cpb.dwh_bi_api.models.UserModel;
import cpb.dwh_bi_api.repositories.UsersRepository;
import cpb.dwh_bi_api.result_set.UserResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private PostgrestConnection postgrestConnection = new PostgrestConnection();

    @Autowired
    private UserResultSet userResultSet;
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PostgrestConnection postgrestConnection = new PostgrestConnection();
        JwtUserModel jwtUserModel = new JwtUserModel();

        try {
            String query = usersRepository.findByUsernameAD(username);

            ResultSet result = postgrestConnection.selectAll(query);

            while (result.next()){
                jwtUserModel.setId(result.getString("id"));
                jwtUserModel.setUsername(result.getString("username"));
                jwtUserModel.setPassword(result.getString("password"));
            }

        }catch (Exception e){

        }

        if (jwtUserModel == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(jwtUserModel.getUsername(), jwtUserModel.getPassword(),
                new ArrayList<>());
    }

    public Boolean getUserAD(String username, String password){
        Boolean isAuth = false;
        try{
            LdapContext ctx = getConnection(username, password,"adcpbank.com");

            UserModel user =  findByUsername(username);

            System.out.println(user.getUsername());

            if(user.getUsername() == null){
                UUID uuid = UUID.randomUUID();
                UserModel newUser = new UserModel();
                newUser.setId(uuid);
                newUser.setFull_name(username.replace('.',' ').toUpperCase());
                newUser.setUsername(username);
                newUser.setPassword("123cpbank!");
                newUser.setCreated_at(LocalDate.now());
                userStore(newUser);
                System.out.println("Create user====>"+newUser.getUsername());

                isAuth = true;
            }else{
                isAuth = true;
            }

            System.out.println("Connection success!..");
            ctx.close();
        }
        catch(Exception e){
            isAuth = false;
            System.out.println("Connection fail.");
            e.printStackTrace();
        }
        return isAuth;
    }

    public UserModel findByUsername(String username) {

        String query = usersRepository.findByUsernameAD(username);

        ResultSet result = postgrestConnection.selectAll(query);

        return userResultSet.getOneAD(result);

    }


    public UserModel userStore(UserModel user) {

        String query = usersRepository.storeAD(user);

        ResultSet result = postgrestConnection.create(query);

        return userResultSet.getOne(result);

    }

    public static LdapContext getConnection(String username, String password, String domainName) throws NamingException {
        return getConnection(username, password, domainName, null);
    }

    public static LdapContext getConnection(String username, String password, String domainName, String serverName) throws NamingException {

        if (domainName == null) {
            try {
                String fqdn = java.net.InetAddress.getLocalHost().getCanonicalHostName();
                if (fqdn.split("\\.").length > 1) domainName = fqdn.substring(fqdn.indexOf(".") + 1);
            } catch (java.net.UnknownHostException e) {
            }
        }

        //System.out.println("Authenticating " + username + "@" + domainName + " through " + serverName);

        if (password != null) {
            password = password.trim();
            if (password.length() == 0) password = null;
        }

        //bind by using the specified username/password
        Hashtable props = new Hashtable();
        String principalName = username + "@" + domainName;
        props.put(Context.SECURITY_PRINCIPAL, principalName);
        if (password != null) props.put(Context.SECURITY_CREDENTIALS, password);


        String ldapURL = "ldap://" + ((serverName == null) ? domainName : serverName + "." + domainName) + '/';
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        props.put(Context.PROVIDER_URL, ldapURL);
        try {
            return new InitialLdapContext(props, null);
        } catch (javax.naming.CommunicationException e) {
            throw new NamingException("Failed to connect to " + domainName + ((serverName == null) ? "" : " through " + serverName));
        } catch (NamingException e) {
            throw new NamingException("Failed to authenticate " + username + "@" + domainName + ((serverName == null) ? "" : " through " + serverName));
        }
    }




}
