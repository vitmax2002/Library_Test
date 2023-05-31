package library.service;

import library.model.Authority;
import library.model.Authority2;
import library.model.User;
import library.repository.AuthorityRepository;
import library.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class UserService {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository,AuthorityRepository authorityRepository)
    {
        this.userRepository=userRepository;
        this.authorityRepository=authorityRepository;
    }

    public User addUser(UserDto userDto)
    {
        User user=new User();
        user.setUsername(userDto.username());
        user.setPassword(userDto.password());
        Authority2 authority2=null;

        for (Authority2 au : Authority2.values()) {
            if (au.name().equals(userDto.authority())) {
                authority2 = au;
                break;
            }
        }
        if(authority2==null)
            throw new NoSuchElementException("Nu este asa element in Enumeratie");
        Authority authority=authorityRepository.findByName(authority2).orElseThrow(()->new NoSuchElementException("Nu exista asa element"));
        user.setAuthority(authority);
        return userRepository.save(user);
    }

    public List<User> showAll()
    {
        return userRepository.findAll();
    }

   // public User update(int id,User user)
   // {
   //     User user2=userRepository.findById(id).orElseThrow(()->new NoSuchElementException("Nu este element cu asa id"));
   //     user2.setAuthority(user.getAuthority());
    //    return userRepository.save(user2);
    //}

}
