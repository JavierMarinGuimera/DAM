def major_prefix(list_mots):
    prim_mot = list_mots[0]

    for index in range(len(prim_mot)):
        for mot in list_mots[1:]:
            if prim_mot[index] != mot[index: index + 1]:
                return prim_mot[0: index]

    return prim_mot


# MAIN:
list_mots = ["casa", "cab", "case"]

finalPrefix = major_prefix(list_mots)
print("Prefix: " + str(finalPrefix))
