package cat.cat_cafe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import cat.cat_cafe.domain.Appuser;
import cat.cat_cafe.domain.AppuserRepository;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    AppuserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Appuser curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(),
                    AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }
    
    
}
