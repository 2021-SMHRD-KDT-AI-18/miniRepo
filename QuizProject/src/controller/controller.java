package controller;

import model.DAO;
import model.DTO;

public class controller {

   private DAO dao = new DAO();

   public int join(DTO dto) {
      
      return dao.join(dto);
   }

   public DTO login(String id, String pw) {
      return dao.login(id,pw);
      
   }
}