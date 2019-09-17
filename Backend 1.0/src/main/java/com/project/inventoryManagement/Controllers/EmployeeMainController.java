package com.project.inventoryManagement.Controllers;


import com.project.inventoryManagement.Models.EmployeeMainModel;
import com.project.inventoryManagement.Models.LoginResponse;
import com.project.inventoryManagement.Repositories.EmployeeMainRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/employees")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class  EmployeeMainController {


    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private EmployeeMainRepository empMainRepo;






    @GetMapping(value = "/hi")
    public String hi(){
/***************BCrypt Hashing methods generate different hashes for same string but can be matched********/

         /************ Method 1 ****************/
//       System.out.println("hash password is : " + BCrypt.hashpw("1234",BCrypt.gensalt()));
//
//            if(BCrypt.checkpw("1234","$2a$10$h.DZLq2fhS96u77uFTANDuOMbWdlCusxxekksnk.pker9OCcftHVO")){
//                System.out.println("Correct");
//            }else{
//                System.out.println("Wronggggggg");
//        }

        /****** method 2 ***************/

//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
//        String encodedPassword = encoder.encode("UserPassword");
//        System.out.println( "Encoded password is: "+ encodedPassword);

            /********method 3***********/
//        Pbkdf2PasswordEncoder encoder2 = new Pbkdf2PasswordEncoder("secret", 10000, 128);
//        String encodedPassword2 = encoder2.encode("UserPassword");
//        System.out.println( "Encoded password is: "+ encodedPassword2);

        /***********method 4***********/

//        String sha256hex = DigestUtils.sha256Hex("1234");
//        System.out.println( "Encoded password is: "+ sha256hex  );

/*************************************************************************************************/

        return "Hiiiiiiiiiiiiii";
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Get all the employees as an array
    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<EmployeeMainModel> getAllUsers() {
        // This returns a JSON or XML with the users
        return empMainRepo.findAll();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Register a new employee & update an employee
    @PostMapping(path="/add") // Map ONLY POST Requests
    public EmployeeMainModel addNewEmployee(@RequestBody EmployeeMainModel m1) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        ////Hashing with Apache Commons Codecs sha256hex
       // m1.setPassword(DigestUtils.sha256Hex(m1.getPassword()));
      System.out.println(m1.toString());

      if(empMainRepo.findByEmail(m1.getEmail()) != null || empMainRepo.findByNic(m1.getNic()) != null){
          System.out.println("Same email-"+ m1.getEmail()+" found. Values are updated");
          EmployeeMainModel m2 = empMainRepo.findByEmail(m1.getEmail());
          System.out.println("****m2:"+ m2.toString());
          empMainRepo.delete(m2);
        //  m1.setEmployeeId(m2.getEmployeeId());
          System.out.println("****m1:"+ m1.toString());
          empMainRepo.save(m1);
      }
      else {
          empMainRepo.save(m1);
      }
        return empMainRepo.findByNic(m1.getNic());
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ///////////////// Don't touch my part below,//////////////////



    @PostMapping(path="/login") // Map ONLY POST Requests
    public LoginResponse login(@RequestBody EmployeeMainModel log1) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        ////Hashing with Apache Commons Codecs sha256hex
        log1.setPassword(DigestUtils.sha256Hex(log1.getPassword()));

        EmployeeMainModel user = empMainRepo.findFirstByEmailAndPassword(log1.getEmail(),log1.getPassword());
        if(user==null){
            return new LoginResponse(null,"fail","User Credentials NOT Valid!!!");
        }else{
            return new LoginResponse(user,"success","User Credentials Valid!!!");
        }

    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

      // Delete an employee by Nic
    @DeleteMapping(path = "/delete")
    public boolean deleteEmployeeByNic(@RequestParam String nic){
        System.out.println("delete nic received: "+ nic);
        try {
            empMainRepo.deleteByNic(nic);
            System.out.println("Deleted");
            return true;
        } catch(Exception e) {
            System.out.println("Exception display as : " + e);
//            EmployeeMainModel mm = empMainRepo.findByNic(nic);
//            for(asset : mm.getAssign(). )
            return false;
        }

    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
