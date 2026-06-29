def filter_order(base_pay, tip, distance):
    ratio = (base_pay + tip) / distance
    return "ACCEPT" if ratio >= 2.0 else "REJECT"
