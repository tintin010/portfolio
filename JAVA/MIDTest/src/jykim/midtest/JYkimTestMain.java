package jykim.midtest;

public class JYkimTestMain {

	public static void main(String[] args) {

		System.out.println("202011272 ������");
		System.out.println();
		
		System.out.println("2) Member ��ü ���� �� ����ϱ�");
		JYkimMember kim = new JYkimMember("������");
		System.out.println(kim);
		JYkimMember h = new JYkimMember("ȫ�浿");
		JYkimMember choi = new JYkimMember("�ֱ浿");
		JYkimMember park = new JYkimMember("�ڱ浿");
		JYkimMember lee = new JYkimMember("�̱浿");
		System.out.println();

		System.out.println("3) Volunteer �߻� Ŭ���� �����");
		System.out.println();

		System.out.println("4) Donation (�������) ��ü �����");
		JYkimVolunteer v1 = new JYkimDonation("��浿 ��� ����",5);
		System.out.println(v1);
		System.out.println();

		System.out.println("5) TalentDonation (��ɱ��) ��ü �����");
		JYkimVolunteer v2 = new JYkimTalentDonation("���� �̿� ����Ȱ��",2, "�̿���");
		System.out.println(v2);
		System.out.println();

		System.out.println("6) VolunteerManager ��ü �����");
		JykimVolunteerManager vm = new JykimVolunteerManager("�׸����󸸵��");
		System.out.println(vm);
		System.out.println();

		System.out.println("7) ����Ȱ�� �߰��ϱ�");
		vm.addVolunteer(new JYkimDonation("��浿 ��� ����",5));
		vm.addVolunteer(new JYkimDonation("��浿 ��� ����",2));
		vm.addVolunteer(new JYkimTalentDonation("���ų��� ���� ����Ȱ��",5, "������"));
		vm.addVolunteer(new JYkimTalentDonation("������ ��� ����Ȱ��",5, "�������"));
		vm.addVolunteer(new JYkimDonation("���浿 ��� ����",55));
		
	}

}
