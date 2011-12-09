package com.acmetelecom.customer;

/**
 * User: javad
 * Date: 08/12/2011
 */
public class CustomerBuilder {

    public static class PlanBuilder {
        private String name, number;

        public PlanBuilder(String name, String number) {
			this.name = name;
            this.number = number;
		}

        public Customer withPricePlan(String pricePlan) {
			return new Customer(name, number, pricePlan);
		}
    }

    public static class NumberBuilder {
		private String name;

		public NumberBuilder(String name) {
			this.name = name;
		}

        public PlanBuilder withPhoneNo(String phoneNo) {
            return new PlanBuilder(name, phoneNo);
        }
	}

	public static class NameBuilder {
		public NameBuilder() {}

		public NumberBuilder named(String name) {
			return new NumberBuilder(name);
		}

	}

	public static NameBuilder aCustomer() {
		return new NameBuilder();
	}

}
