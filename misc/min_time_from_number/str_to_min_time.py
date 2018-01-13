NA = 'NA'


def to_time_triple(num_list):
    return num_list[0] * 10 + num_list[1], num_list[2] * 10 + num_list[3], num_list[4] * 10 + num_list[5]


def fmt_time(lst):
    return '{:02d}:{:02d}:{:02d}'.format(*to_time_triple(lst))


# def is_possible_time(num_list):
#     if num_list[0] < 0 or num_list[0] > 2 or num_list[2] > 5 or (
#                     num_list[0] == 2 and (num_list[1] > 3 or num_list[3] > 5)):
#         return False
#
#     return True


def is_valid_time(num_list):
    time_tuple = to_time_triple(num_list)
    if time_tuple[2] < 60 and time_tuple[1] < 60 and time_tuple[0] < 24:
        return True
    else:
        return False


def swap_indices(lst, i1, i2):
    if i1 != i2:
        lst[i1], lst[i2] = lst[i2], lst[i1]


def find_valid_val_index(num_list, index, predicate):
    for i in range(index, -1, -1):
        if predicate(num_list[i]):
            return i
    return index


def str_to_min_time(input_str):
    if not input_str or len(input_str) != 6:
        return NA

    num_list = sorted([int(x) for x in input_str])

    for i in (4, 2):
        swap_indices(num_list, i, find_valid_val_index(num_list, i, lambda x: x < 6))

    if is_valid_time(num_list):
        return fmt_time(num_list)
    else:
        return NA


def main():
    assert str_to_min_time('000000') == '00:00:00'
    assert str_to_min_time('024095') == '00:24:59'
    assert str_to_min_time('333333') == NA
    assert str_to_min_time('244444') == NA
    assert str_to_min_time('026666') == NA
    assert str_to_min_time('123566') == '12:36:56'
    assert str_to_min_time('123666') == '16:26:36'
    assert str_to_min_time('223666') == NA


if __name__ == '__main__':
    main()
