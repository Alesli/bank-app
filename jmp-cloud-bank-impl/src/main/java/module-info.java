import com.domain.jmp.bank.api.BankService;
import com.domain.jmp.cloud.bank.impl.BankServiceImpl;

module bank.impl {
    requires transitive bank.api;
    requires dto;
    provides BankService with BankServiceImpl;
}
