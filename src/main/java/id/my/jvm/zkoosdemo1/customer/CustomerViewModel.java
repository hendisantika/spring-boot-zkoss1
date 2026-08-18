package id.my.jvm.zkoosdemo1.customer;

import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

/**
 * Created by IntelliJ IDEA.
 * Project : zkoos-demo1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/08/26
 * Time: 22.22
 */
@VariableResolver(DelegatingVariableResolver.class)
public class CustomerViewModel {

    @WireVariable
    private CustomerService customerService;

    private ListModelList<Customer> customers;
    private Customer selected;
    private boolean showForm;

    @Init
    public void init() {
        reload();
    }

    private void reload() {
        List<Customer> all = customerService.findAll();
        if (customers == null) {
            customers = new ListModelList<>(all);
        } else {
            customers.clear();
            customers.addAll(all);
        }
    }

    public ListModelList<Customer> getCustomers() {
        return customers;
    }

    public Customer getSelected() {
        return selected;
    }

    public boolean isShowForm() {
        return showForm;
    }

    @Command
    @NotifyChange({"selected", "showForm"})
    public void newCustomer() {
        selected = new Customer();
        showForm = true;
    }

    @Command
    @NotifyChange({"selected", "showForm"})
    public void edit(@BindingParam("customer") Customer customer) {
        selected = copyOf(customer);
        showForm = true;
    }

    @Command
    @NotifyChange({"customers", "selected", "showForm"})
    public void save() {
        if (selected == null || selected.getName() == null || selected.getName().isBlank()) {
            Messagebox.show("Name is required", "Validation Error", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        customerService.save(selected);
        reload();
        selected = null;
        showForm = false;
    }

    @Command
    @NotifyChange({"selected", "showForm"})
    public void cancel() {
        selected = null;
        showForm = false;
    }

    @Command
    public void delete(@BindingParam("customer") Customer customer) {
        Messagebox.show("Delete customer \"" + customer.getName() + "\"?", "Confirm Delete",
                Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                (EventListener<Event>) event -> {
                    if (Messagebox.ON_YES.equals(event.getName())) {
                        customerService.deleteById(customer.getId());
                        reload();
                        BindUtils.postNotifyChange(null, null, CustomerViewModel.this, "customers");
                    }
                });
    }

    private Customer copyOf(Customer source) {
        Customer copy = new Customer();
        copy.setId(source.getId());
        copy.setName(source.getName());
        copy.setEmail(source.getEmail());
        copy.setPhone(source.getPhone());
        copy.setAddress(source.getAddress());
        return copy;
    }
}
