package org.tfoc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class CodecTest {

    @Test
    void testCodec() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        Codec serializer = new Codec();
        Codec deserializer = new Codec();
        TreeNode ans = deserializer.deserialize(serializer.serialize(root));

        assertAll(
                () -> assertEquals(root.val, ans.val),
                () -> assertEquals(root.right.val, ans.right.val),
                () -> assertEquals(root.left.val, ans.left.val),
                () -> assertEquals(root.right.right.val, ans.right.right.val),
                () -> assertEquals(root.right.left.val, ans.right.left.val),
                () -> assertEquals(root.left.val, ans.left.val)
        );
    }

    @Test
    void serialize() {
        String seralizeExpected = "1,2,white,white,3,4,white,white,5,white,white";

        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);
        t1.right.left = new TreeNode(4);
        t1.right.right = new TreeNode(5);

        Codec code = new Codec();
        String serialize = code.serialize(t1);

        assertEquals(serialize, seralizeExpected);
    }

    @Test
    void serialize_1() {
        String seralizeExpected = "20,8,4,white,white,12,10,white,white,14,white,white,22,white,white";

        TreeNode t1 = new TreeNode(20);
        t1.left = new TreeNode(8);
        t1.left.left = new TreeNode(4);
        t1.left.right = new TreeNode(12);
        t1.left.right.left = new TreeNode(10);
        t1.left.right.right = new TreeNode(14);
        t1.right = new TreeNode(22);

        Codec code = new Codec();
        String serialize = code.serialize(t1);

        assertEquals(serialize, seralizeExpected);
    }

    @Test
    void deserialize() {
        String seralizeExpected = "20,4,8,white,white,12,10,white,white,14,white,white,22,white,white";

        Codec codec = new Codec();
        TreeNode res = codec.deserialize(seralizeExpected);

        assertAll(
                () -> assertEquals(20, res.val),
                () -> assertEquals(4, res.left.val),
                () -> assertEquals(8, res.left.left.val),
                () -> assertEquals(12, res.left.right.val),
                () -> assertEquals(10, res.left.right.left.val),
                () -> assertEquals(14, res.left.right.right.val),
                () -> assertEquals(22, res.right.val)
        );
    }

    @Test
    void deserialize_1() {
        String seralizeExpected = "20,8,white,white,22,white,white";

        Codec codec = new Codec();
        TreeNode res = codec.deserialize(seralizeExpected);

        assertAll(
                () -> assertEquals(20, res.val),
                () -> assertEquals(8, res.left.val),
                () -> assertEquals(22, res.right.val)
        );
    }
}