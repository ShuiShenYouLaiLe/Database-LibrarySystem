package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.*;
import com.databaseproject.library_system.model.*;
import com.databaseproject.library_system.service.ReaderService;
import com.databaseproject.library_system.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Pattern;

@Controller
public class AdminApp {
    @Autowired
    private ReaderService readerService;

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    BranchRepository branchRepository;

    @GetMapping("/admin/page")
    public String checkAdmin(Admin admin){
        if (admin == null || admin.getName().isEmpty() || admin.getPassword().isEmpty()) return "failed_login";
        if (admin.getName().equals("root") && admin.getPassword().equals("12345678")) return "admin_op_page";
        return "failed_login";
    }

    @GetMapping("/admin/op")
    public String toOpPage() {
        return "admin_op_page";
    }

    @GetMapping("/admin/copy")
    public String toCopyInputPage(Model model) {
        model.addAttribute("copy", new Copy());
        model.addAttribute("copyId", new Copy.CopyId());
        return "admin_copy_input_page";
    }

    @GetMapping("/admin/copy/status")
    public String toCopyStatusInputPage(Model model) {
        model.addAttribute("copyId", new Copy.CopyId());
        return "admin_copy_status_input_page";
    }

    @GetMapping("/admin/reader")
    public String toReaderInputPage(Model model) {
        model.addAttribute("reader", new Reader());
        return "reader_input_page";
    }

    @GetMapping("/special/query/1")
    public String toQueryOneInput(Model model) {
        model.addAttribute("input", new QueryOneInput());
        return "admin_query_1_input";
    }

    @GetMapping("/topN/branch")
    public String getTopNByBranch(@RequestParam Integer N, @RequestParam Long I, Model model) {
        Branch branch = null;
        try {
            branch = branchRepository.findById(I).get();
        } catch (Exception e) {

        }
        if (branch == null) {
            model.addAttribute("str", "branch with this id does not exists!");
            return "ADMIN_FAILED_WITH_MESSAGE";
        }
        List<Object[]> list = readerRepository.findTopNByBranch(I, N);
        if (list.isEmpty()) {
            model.addAttribute("str", "no borrow transaction found!");
            return "ADMIN_FAILED_WITH_MESSAGE";
        }
        List<BorrowerWithCount> res = EntityUtils.castEntity(list, BorrowerWithCount.class, new BorrowerWithCount());
        model.addAttribute("details", res);
        model.addAttribute("str", "Most frequent " + N + " borrowers in branch " + I);
        return "most_frequent_borrow_details";
    }

    @GetMapping("/special/query/2")
    public String toQueryTwoInput(Model model) {
        model.addAttribute("input", new QueryTwoInput());
        return "admin_query_2_input";
    }

    @GetMapping("/topN")
    public String getTopNFromAll(@RequestParam int N, Model model) {
        List<Object[]> list = readerRepository.findTopNFromAll(N);
        if (list.isEmpty()) {
            model.addAttribute("str", "no borrow transaction found!");
            return "ADMIN_FAILED_WITH_MESSAGE";
        }
        List<BorrowerWithCount> res = EntityUtils.castEntity(list, BorrowerWithCount.class, new BorrowerWithCount());
        model.addAttribute("details", res);
        model.addAttribute("str", "Most frequent " + N + " borrowers in library");
        return "most_frequent_borrow_details";
    }

    @GetMapping("/special/query/3")
    public String toQueryThreeInput(Model model) {
        model.addAttribute("input", new QueryOneInput());
        return "admin_query_3_input";
    }

    @GetMapping("/topN/branch/document")
    public String getTopNDocByBranch(@RequestParam Integer N, @RequestParam Long I, Model model) {
        Branch branch = null;
        try {
            branch = branchRepository.findById(I).get();
        } catch (Exception e) {

        }
        if (branch == null) {
            model.addAttribute("str", "branch with this id does not exists!");
            return "ADMIN_FAILED_WITH_MESSAGE";
        }
        List<Object[]> list = readerRepository.findTopNDocFromBranch(I, N);
        if (list.isEmpty()) {
            model.addAttribute("str", "no borrow transaction found!");
            return "ADMIN_FAILED_WITH_MESSAGE";
        }
        List<BorrowedBookWithCount> res = EntityUtils.castEntity(list, BorrowedBookWithCount.class, new BorrowedBookWithCount());
        model.addAttribute("details", res);
        model.addAttribute("str", "Most frequent borrowed " + N + " documents in branch " + I);
        return "most_frequent_borrow_document_details";
    }

    @GetMapping("/special/query/4")
    public String toQueryFourInput(Model model) {
        model.addAttribute("input", new QueryOneInput());
        return "admin_query_4_input";
    }

    @GetMapping("/topN/document")
    public String getTopNDocumentFromAll(@RequestParam int N, Model model) {
        List<Object[]> list = readerRepository.findTopNDocFromAll(N);
        if (list.isEmpty()) {
            model.addAttribute("str", "no borrow transaction found!");
            return "ADMIN_FAILED_WITH_MESSAGE";
        }
        List<BorrowedBookWithCount> res = EntityUtils.castEntity(list, BorrowedBookWithCount.class, new BorrowedBookWithCount());
        model.addAttribute("details", res);
        model.addAttribute("str", "Most frequent borrowed " + N + " documents in library");
        return "most_frequent_borrow_document_details";
    }

    @GetMapping("/special/query/5")
    public String toQueryFiveInput(Model model) {
        model.addAttribute("input", new QueryFiveInput());
        return "admin_query_5_input";
    }

    @GetMapping("/top10/year")
    public String getTopNFromAll(@RequestParam String Y, Model model) {
        String year = Y + "%";
        List<Object[]> list = readerRepository.findTopTenDocByYear(year);
        if (list.isEmpty()) {
            model.addAttribute("str", "no borrow transaction found!");
            return "ADMIN_FAILED_WITH_MESSAGE";
        }
        List<BorrowedBookWithCount> res = EntityUtils.castEntity(list, BorrowedBookWithCount.class, new BorrowedBookWithCount());
        model.addAttribute("details", res);
        model.addAttribute("str", "Most frequent borrowed 10 documents in year " + Y);
        return "most_frequent_borrow_document_details";
    }

    @GetMapping("/special/query/6")
    public String toQuerySixInput(Model model) {
        model.addAttribute("input", new QuerySixInput());
        return "admin_query_6_input";
    }

    @GetMapping("/average/fine")
    public String getAverageFine(Model model, String from, String to) {
        if (!isFormatCorrect(from) || !isFormatCorrect(to)) {
            model.addAttribute("str", "please check your input format");
            return "ADMIN_FAILED_WITH_MESSAGE";
        }

        List<Object[]> list = readerRepository.findTopTenDocByYear(from, to);
        if (list.isEmpty()) {
            model.addAttribute("str", "no data found in the given period");
            return "ADMIN_FAILED_WITH_MESSAGE";
        }
        List<BorrowTransactionByBranch> res = EntityUtils.castEntity(list, BorrowTransactionByBranch.class, new BorrowTransactionByBranch());
        Map<BigInteger, List<Double>> fineMap = new HashMap<>();
        Map<BigInteger, BranchAverageFine> resMap= new HashMap<>();
        long current = new Date().getTime();
        for (BorrowTransactionByBranch tran: res) {
            if (tran.getRet_date_time() == null) {
                tran.setRet_date_time(new Timestamp(current));
            }
            if (!fineMap.containsKey(tran.getBid())) {
                fineMap.put(tran.getBid(), new ArrayList<>());
            }
            if (!resMap.containsKey(tran.getBid())) {
                BranchAverageFine fine = new BranchAverageFine();
                fine.setAverage_fine("");
                fine.setBid(tran.getBid());
                fine.setName(tran.getName());
                resMap.put(tran.getBid(), fine);
            }
            List<Double> fines = fineMap.get(tran.getBid());
            fines.add(calcFine(tran.getBor_date_time(), tran.getRet_date_time()));
        }
        List<BranchAverageFine> resList = new ArrayList<>();
        for (BigInteger key: fineMap.keySet()) {
            List<Double> fine = fineMap.get(key);
            double sum = 0.0;
            for (double f: fine) {
                sum += f;
            }
            BranchAverageFine branch = resMap.get(key);
            branch.setAverage_fine(String.format("%.2f", sum / fine.size()));
            resList.add(branch);
        }
        model.addAttribute("details", resList);
        model.addAttribute("str", "Here are all branch data between" + from + " and " + to);
        return "branch_average_fine";
    }

    private boolean isFormatCorrect(String date) {
        return Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}").matcher(date).matches();
    }

    private double calcFine(Timestamp from, Timestamp to) {
        long current = to.getTime();
        long prev = from.getTime();
        if (current - prev <= 86400000l * 20) return 0.0;
        return (((current - prev) + 86400000l) / 86400000l) * 0.2 - 4.0;
    }
}
