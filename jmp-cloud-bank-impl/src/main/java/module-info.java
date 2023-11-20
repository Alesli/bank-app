module bank.impl {
    requires transitive bank.api;
    requires dto;
    provides com.domain.jmp.bank.api.Bank with com.domain.jmp.cloud.bank.impl.BankImpl;
}
