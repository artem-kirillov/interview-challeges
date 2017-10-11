package com.leetcode;

import org.junit.Test;
import static org.junit.Assert.*;

public class Problem76MinimumWindowSubstringTest {
    @Test
    public void testWindow() {
        assertEquals("agwlpxpaevlqnvpehhiqfidr", new Problem76MinimumWindowSubstring().minWindow("eccdmbeygbucwrqllyocfmnhartzdspkwrnksrjbvdftwyehwtkdhxkjqeiwyyguptfkmqnjvscrqjzoelrnkcwbgfxgffawyhimzvwuqaoilpxpaqcksznagmglrjhrssesncofeeqqtphvfkiylvatijgbsptmhxgvbxcfbsvxjdsppapcypxiydjssefablxckbkmufjfkdubgjfebcozfzuaezafmzkxaulwbwcozwrhizljbbijbcjfdlmskwrkscjwalmzjdhrcuagwlpxpaevlqnvpehhiqfidrjnxsebnhhluykjyoglgvivbsfjiktoedbbxfemomximjunzaygwjdzbijlvezzjachigtwuthhcbwedumzfrbgaksxqryauahciaafthoyikemgqnmhosjamgrggtaiaeraewwmkwwwakcsaozqjltxtkdaddifhbhozdclbahblmtncvdngwlbiobnumsbparpftahwkkhwguabzojwzmenouhcmlroegwajdwvpdtvztyahjwgwdwmxfolqfcvoyxzbtusynozgozqywywbuiycwlsdnprlmqohmhfzxeineyncwjdtrdvpoptehmrvfgdrpiropzdtxrvvayqudljrdcxkrphwvknmvgckfffewaczastdgewjhuvajcwgnlukswwzdbgcdib",
                "fawiiagr"));

        assertEquals("fzzvqprlizelwmkjchwtcdbvpbosminsjpncehnmgtzegk", new Problem76MinimumWindowSubstring().minWindow("qdsvbyuipqxnhkbgqdgozelvapgcainsofnrfjzvawihgmpwpwnqcylcnufqcsiqzwhhhjchfmqmagkrexigytklnrdslmkniuurdwzikrwlxhcbgkjegwsvnvpzhamrwgjzekjobizbreexqqewmwubtjadlowhwhiarurvcsyvwcunsylgwhisrivezrmvzwwsqppuhnreqmtkkgrjozbhjwlkpzgqwejotylamcgeqzobihmwinduloecqmtoqcejcpmqusukulncsbabodxbtbeloxzgbesdveupyocuzryutyxjdulzvpklokspqkslqodqfhlgajatkxfntqorhzcxlwmdigoyxtrcccidnlyxidnevdveczbpwpugyjhveyxhcfkpqipboehjhcombrdzhyghjncnnzwpggezrvcfzjqjngvoyyqhwwohlsvarrpzavatrcasnqbazyrzxhivfydsqasjtjiteloxposdhtfgswhrfpomnteftyonjyiojxnznfeubjctijmnyaanwgsphieqhpgsoutbbxycjaxrklekogakpsbwdimkxvelpyosvmxgnuxzgejwmjgbehxhpmtohzbyxqsvepbrmzsufcqrnwttfscxgxlpxnpufirjxtdjuvfzzvqprlizelwmkjchwtcdbvpbosminsjpncehnmgtzegknkrmdvrhrgihywsoobdedhltvtmxzuhmeaakysrpybyzxqnouqszzfswahtzbanidoubilsgoqfnjubdmvclaxkaedbfeppj",
                "fjknwevk"));

        assertEquals("ccbbccaababcaccbccbbabcbc", new Problem76MinimumWindowSubstring().minWindow("baaccacaccacbcaabbccbbccaababcaccbccbbabcbc", "bacbcacccbccbabcbbacbcbc"));

        assertEquals("aabcabbcab", new Problem76MinimumWindowSubstring().minWindow("aacbaccccaabcabbcab", "bcbbacaaab"));

        assertEquals("BANC", new Problem76MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC"));

        assertEquals("a", new Problem76MinimumWindowSubstring().minWindow("a", "a"));
        assertEquals("aa", new Problem76MinimumWindowSubstring().minWindow("aa", "aa"));
        assertEquals("ab", new Problem76MinimumWindowSubstring().minWindow("abc", "ab"));

        assertEquals("", new Problem76MinimumWindowSubstring().minWindow("BBAbBB", "ABbb"));

        assertEquals("baca", new Problem76MinimumWindowSubstring().minWindow("acbbaca", "aba"));
        assertEquals("aec", new Problem76MinimumWindowSubstring().minWindow("cabefgecdaecf", "cae"));
        assertEquals("cwae", new Problem76MinimumWindowSubstring().minWindow("cabwefgewcwaefgcf","cae"));
        assertEquals("aaa", new Problem76MinimumWindowSubstring().minWindow("aaflslflsldkalskaaa", "aaa"));
    }

    @Test
    public void curTest() {
        assertEquals("aa", new Problem76MinimumWindowSubstring().minWindow("aa", "aa"));
    }
}
