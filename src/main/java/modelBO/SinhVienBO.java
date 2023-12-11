package modelBO;

import modelBEAN.SinhVien;
import modelDAO.SinhVienDAO;

import java.sql.SQLException;

public class SinhVienBO {
    public SinhVien getSinhVienById(int IDSV){
        SinhVienDAO sinhVienDAO = null;
        try {
            sinhVienDAO = new SinhVienDAO();
            return  sinhVienDAO.getSVByIDSV(IDSV);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
